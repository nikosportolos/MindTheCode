using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Timesheet.Mappers;
using Timesheet.Models.Entities;
using Timesheet.Models.ViewModels;
using Timesheet.Repositories;

namespace Timesheet.Controllers
{
    public class TimesheetEntryController : Controller
    {
        private readonly ITimesheetEntryRepository _timesheetEntryRepository;
        private readonly IProjectRepository _projectRepository;
        private readonly IUserRepository _userRepository;
        private readonly ITimesheetEntryMapper _mapper;
        private readonly UserManager<User> _userManager;

        public TimesheetEntryController(ITimesheetEntryRepository timesheetEntryRepository, ITimesheetEntryMapper mapper, UserManager<User> userManager, IProjectRepository projectRepository, IUserRepository userRepository)
        {
            _timesheetEntryRepository = timesheetEntryRepository;
            _projectRepository = projectRepository;
            _userRepository = userRepository;
            _mapper = mapper;
            _userManager = userManager;
        }

        [HttpGet]
        public async Task<IActionResult> Index()
        {
            var userId = User.FindFirstValue(ClaimTypes.NameIdentifier);
            var user = await _userManager.FindByIdAsync(userId);
            var roles = await _userManager.GetRolesAsync(user);

            List<TimesheetEntry> entries;
            if (roles.Contains("Administrator"))
            {
                entries = (await _timesheetEntryRepository.GetAll()).ToList();
            }
            else
            {
                if (roles.Contains("Manager"))
                {
                    entries = _timesheetEntryRepository.GetTimesheetEntriesForManager(user);
                }
                else
                {
                    entries = _timesheetEntryRepository.GetTimesheetEntriesForEmployee(user);
                }
            }

            var viewModels = await _mapper.ConvertToViewModels(entries);
            foreach (TimesheetEntryViewModel vm in viewModels)
            {
                User u = await _userRepository.GetByGuid(vm.UserId);
                if (u != null)
                    vm.UserFullName = string.Format("{0} {1}", u.FirstName, u.LastName);

                Project p = await _projectRepository.GetById(vm.ProjectId);
                if (p != null)
                    vm.ProjectName = p.Name;
            }

            return View(viewModels);
        }

        [HttpGet]
        public async Task<IActionResult> Create()
        {
            ViewBag.Projects = new SelectList(await _projectRepository.GetAll(), "Id", "Name");
            ViewBag.Users = new SelectList(await _userRepository.GetAll(), "Id", "Email");
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Create(TimesheetEntryViewModel viewModel)
        {
            User user = await _userRepository.GetByGuid(User.FindFirstValue(ClaimTypes.NameIdentifier));
            viewModel.UserId = user.Id;
            viewModel.UserFullName = string.Format("{0} {1}", user.FirstName, user.LastName);
            viewModel.ProjectName = (await _projectRepository.GetById(viewModel.ProjectId)).Name;

            // Add TimesheetEntry to database
            await _timesheetEntryRepository.Create(await _mapper.ConvertFromViewModel(viewModel));

            // Return to Index
            return RedirectToAction(nameof(Index));
        }

        public async Task<IActionResult> Details(int id)
        {
            TimesheetEntry entry = await _timesheetEntryRepository.GetById(id);
            return View(await _mapper.ConvertToViewModel((entry)));
        }

        public async Task<IActionResult> Edit(int id)
        {
            TimesheetEntry entry = await _timesheetEntryRepository.GetById(id);
            ViewBag.HeadFullName = String.Format("{0} {1}", entry.User.FirstName, entry.User.LastName);
            return View(await _mapper.ConvertToViewModel((entry)));
        }

        [HttpPost]
        public async Task<IActionResult> Edit(TimesheetEntryViewModel viewModel)
        {
            TimesheetEntry entry = await _mapper.ConvertFromViewModel(viewModel);
            await _timesheetEntryRepository.Update(entry);
            return RedirectToAction(nameof(Details), "TimesheetEntry", new { id = entry.Id });
        }

        public async Task<IActionResult> Delete(int id)
        {
            TimesheetEntry entry = await _timesheetEntryRepository.GetById(id);
            return View(await _mapper.ConvertToViewModel((entry)));
        }

        [HttpPost]
        public async Task<IActionResult> Delete(TimesheetEntryViewModel viewModel)
        {
            var entry = await _mapper.ConvertFromViewModel(viewModel);
            await _timesheetEntryRepository.Delete(entry.Id);
            return RedirectToAction(nameof(Index));
        }

    }
}
