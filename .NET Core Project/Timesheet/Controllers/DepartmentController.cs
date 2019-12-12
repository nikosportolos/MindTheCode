using System;
using System.Collections.Generic;
using System.Linq;
using System.Security.Claims;
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
    public class DepartmentController : Controller
    {
        private readonly IDepartmentRepository _departmentRepository;
        private readonly IUserRepository _userRepository;
        private readonly UserManager<User> _userManager;
        private readonly IDepartmentMapper _mapper;

        public DepartmentController([FromServices] IDepartmentRepository departmentRepository, IDepartmentMapper mapper, UserManager<User> userManager, IUserRepository userRepository)
        {
            _departmentRepository = departmentRepository;
            _userRepository = userRepository;
            _mapper = mapper;
            _userManager = userManager;
        }

        // GET: Department
        [HttpGet]
        public async Task<IActionResult> Index()
        {
            List<Department> departments = (await _departmentRepository.GetAll()).ToList();
            return View(_mapper.ConvertToViewModels(departments));
        }

        // GET: Department/Details/5
        public async Task<IActionResult> Details(int id)
        {
            Department department = await _departmentRepository.GetById(id);
            User manager = await _userRepository.GetByGuid(department.DepartmentHeadId);
            ViewBag.HeadFullName = String.Format("{0} {1}", manager.FirstName, manager.LastName);
            return View(_mapper.ConvertToViewModel((department)));
        }

        // GET: Department/Create
        [HttpGet]
        public async Task<IActionResult> Create()
        {
            ViewBag.Users = new SelectList(await _userRepository.GetAll(), "Id", "Email");
            return View();
        }

        // POST: Department/Create
        [HttpPost]
        public async Task<IActionResult> Create(DepartmentViewModel viewModel)
        {
            await _departmentRepository.Create(_mapper.ConvertFromViewModel(viewModel));
            return RedirectToAction(nameof(Index), "Department");
        }

        // GET: Department/Edit/5
        public async Task<IActionResult> Edit(int id)
        {
            Department department = await _departmentRepository.GetById(id);
            ViewBag.Users = new SelectList(await _userRepository.GetAll(), "Id", "Email");
            return View(_mapper.ConvertToViewModel((department)));
        }

        // POST: Department/Edit/5
        [HttpPost]
        public async Task<IActionResult> Edit(DepartmentViewModel viewModel)
        {
            Department department = _mapper.ConvertFromViewModel(viewModel);
            await _departmentRepository.Update(department);
            return RedirectToAction(nameof(Details), "Department", new { id = department.Id });
        }

        // GET: Department/Delete/5
        public async Task<IActionResult> Delete(int id)
        {
            Department department = await _departmentRepository.GetById(id);
            return View(_mapper.ConvertToViewModel(department));
        }

        // POST: Department/Delete/5
        [HttpPost]
        public async Task<IActionResult> Delete(DepartmentViewModel viewModel)
        {
            Department department = _mapper.ConvertFromViewModel(viewModel);
            await _departmentRepository.Delete(department.Id);
            return RedirectToAction(nameof(Index));
        }
    }
}