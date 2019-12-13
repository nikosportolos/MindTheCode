using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Microsoft.AspNetCore.Mvc.Rendering;
using Timesheet.Mappers;
using Timesheet.Models.Entities;
using Timesheet.Models.ViewModels;
using Timesheet.Repositories;

namespace Timesheet.Controllers
{
    public class UserController : Controller
    {
        private readonly IUserRepository _userRepository;
        private readonly IDepartmentRepository _departmentRepository;
        private readonly UserManager<User> _userManager;
        private readonly IUserMapper _mapper;

        public UserController([FromServices] IUserRepository userRepository, IUserMapper mapper, UserManager<User> userManager, IDepartmentRepository departmentRepository)
        {
            _userRepository = userRepository;
            _departmentRepository = departmentRepository;
            _mapper = mapper;
            _userManager = userManager;
        }

        // GET: User
        public async Task<IActionResult> Index()
        {
            List<User> users = (await _userRepository.GetAll()).ToList();
            var viewModels = _mapper.ConvertToViewModels(users);

            foreach (var vm in viewModels)
            {
                var dept = await _departmentRepository.GetById(vm.DepartmentId);
                if (dept != null)
                    vm.DepartmentName = dept.Name;
                else
                    vm.DepartmentName = "N/A";
            }

            return View(viewModels);
        }

        // GET: User/Details/5
        public async Task<ActionResult> DetailsAsync(string id)
        {
            User user = await _userRepository.GetByGuid(id);
            return View(_mapper.ConvertToViewModel(user));
        }

        // GET: User/Create
        public async Task<IActionResult> Create()
        {
            ViewBag.Departments = new SelectList(await _departmentRepository.GetAll(), "Id", "Name");
            return View();
        }

        // POST: User/Create
        [HttpPost]
        public async Task<IActionResult> Create(UserViewModel viewModel)
        {
            viewModel.Department = await _departmentRepository.GetById(viewModel.DepartmentId);
            User user = _mapper.ConvertFromViewModel(viewModel);         
            await _userRepository.Create(user);
            return RedirectToAction(nameof(Index));
        }

        // GET: User/Edit/5      
        public async Task<IActionResult> Edit(string id)
        {
            User user = await _userRepository.GetByGuid(id);
            ViewBag.Departments = new SelectList(await _departmentRepository.GetAll(), "Id", "Name");
            return View(_mapper.ConvertToViewModel(user));
        }

        // POST: User/Edit/5
        [HttpPost]
        public async Task<IActionResult> Edit(UserViewModel viewModel)
        {
            User user = _mapper.ConvertFromViewModel(viewModel);
            await _userRepository.Update(user);
            return RedirectToAction(nameof(Index));
        }

        // GET: User/Delete/5
        public async Task<IActionResult> Delete(string id)
        {
            User user = await _userRepository.GetByGuid(id);
            return View(_mapper.ConvertToViewModel(user));
        }

        // POST: User/Delete/5
        [HttpPost]
        public async Task<IActionResult> Delete(UserViewModel viewModel)
        {
          //  User user = _mapper.ConvertFromViewModel(viewModel);
            await _userRepository.DeleteByGuid(viewModel.Id);
            return RedirectToAction(nameof(Index));
        }
    }
}