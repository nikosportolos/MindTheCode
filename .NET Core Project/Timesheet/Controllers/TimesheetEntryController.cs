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
        private readonly ITimesheetEntryMapper _mapper;
        private readonly UserManager<User> _userManager;

        public TimesheetEntryController(ITimesheetEntryRepository timesheetEntryRepository, ITimesheetEntryMapper mapper, UserManager<User> userManager, IProjectRepository projectRepository)
        {
            _timesheetEntryRepository = timesheetEntryRepository;
            _projectRepository = projectRepository;
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
                entries = _timesheetEntryRepository.GetAll().ToList();
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

            return View(_mapper.ConvertToViewModels(entries));
        }

        [HttpGet]
        public IActionResult Create()
        {
            ViewBag.Projects = new SelectList(_projectRepository.GetAll(), "ID", "Name");
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Create(TimesheetEntryViewModel viewModel)
        {
            await _timesheetEntryRepository.Create(_mapper.ConvertFromViewModel(viewModel));
            return RedirectToAction(nameof(Index));
        }

        public async Task<IActionResult> Details(int id)
        {
            TimesheetEntry entry = await _timesheetEntryRepository.GetById(id);
            return View(_mapper.ConvertToViewModel((entry)));
        }

        public async Task<IActionResult> Edit(int id)
        {
            TimesheetEntry entry = await _timesheetEntryRepository.GetById(id);
            return View(_mapper.ConvertToViewModel((entry)));
        }

        [HttpPost]
        public async Task<IActionResult> Edit(TimesheetEntryViewModel viewModel)
        {
            TimesheetEntry entry = _mapper.ConvertFromViewModel(viewModel);
            await _timesheetEntryRepository.Update(entry);
            return RedirectToAction(nameof(Details), "TimesheetEntry", new { id = entry.ID });
        }

        public async Task<IActionResult> Delete(int id)
        {
            TimesheetEntry entry = await _timesheetEntryRepository.GetById(id);
            return View(_mapper.ConvertToViewModel((entry)));
        }

        [HttpPost]
        public async Task<IActionResult> Delete(TimesheetEntryViewModel viewModel)
        {
            TimesheetEntry entry = _mapper.ConvertFromViewModel(viewModel);
            await _timesheetEntryRepository.Delete(entry.ID);
            return RedirectToAction(nameof(Index));
        }

    }
}
