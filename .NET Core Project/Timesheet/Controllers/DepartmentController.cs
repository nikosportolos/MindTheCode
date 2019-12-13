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
            foreach (var x in departments)
                x.DepartmentHead = (await _userRepository.GetByGuid(x.DepartmentHeadId));
            return View(_mapper.ConvertToViewModels(departments));
        }

        // GET: Department/Details/5
        public async Task<IActionResult> Details(int id)
        {
            Department department = await _departmentRepository.GetById(id);
            User manager = await _userRepository.GetByGuid(department.DepartmentHeadId);
            ViewBag.HeadFullName = String.Format("{0} {1}", manager.FirstName, manager.LastName);
            return View(_mapper.ConvertToViewModel(department));
        }

        // GET: Department/Create
        [HttpGet]
        public async Task<IActionResult> Create()
        {
            var departments = await _departmentRepository.GetAll();
            List<User> managers = (await _userRepository.GetAll()).ToList();

            foreach (Department department in departments)
            {
                var u = managers.Where<User>(u => u.Id == department.DepartmentHeadId).First();
                if (u != null && !string.IsNullOrEmpty(u.Email))
                {
                    managers.Remove(u);
                }
            }

            ViewBag.Users = new SelectList(managers, "Id", "Email");
            return View();
        }

        // POST: Department/Create
        [HttpPost]
        public async Task<IActionResult> Create(DepartmentViewModel viewModel)
        {
            User manager = await _userRepository.GetByGuid(viewModel.DepartmentHeadId);
            Department department = _mapper.ConvertFromViewModel(viewModel, manager);
            await _departmentRepository.Create(department);
            return RedirectToAction(nameof(Index), "Department");
        }

        // GET: Department/Edit/5
        public async Task<IActionResult> Edit(int id)
        {
            Department department = await _departmentRepository.GetById(id);
            ViewBag.Users = new SelectList(await _userRepository.GetAll(), "Id", "Email", department.DepartmentHeadId);
            return View(_mapper.ConvertToViewModel(department));
        }

        // POST: Department/Edit/5
        [HttpPost]
        public async Task<IActionResult> Edit(DepartmentViewModel viewModel)
        {
            User manager = await _userRepository.GetByGuid(viewModel.DepartmentHeadId);
            Department department = _mapper.ConvertFromViewModel(viewModel, manager);
            await _departmentRepository.Update(department);
            return RedirectToAction(nameof(Details), "Department", new { id = department.Id });
        }

        // GET: Department/Delete/5
        public async Task<IActionResult> Delete(int id)
        {
            Department department = await _departmentRepository.GetById(id);
            User manager = await _userRepository.GetByGuid(department.DepartmentHeadId);
            ViewBag.HeadFullName = String.Format("{0} {1}", manager.FirstName, manager.LastName);
            return View(_mapper.ConvertToViewModel(department));
        }

        // POST: Department/Delete/5
        [HttpPost]
        public async Task<IActionResult> Delete(DepartmentViewModel viewModel)
        {
            try
            {
                await _departmentRepository.Delete(viewModel.Id);
            }
            catch (Exception ex)
            {
                return RedirectToAction(nameof(DeleteError), "Department", new { id = viewModel.Id });
            }

            return RedirectToAction(nameof(Index));
        }

        public async Task<IActionResult> DeleteError(int id)
        {
            Department department = await _departmentRepository.GetById(id);
            User manager = await _userRepository.GetByGuid(department.DepartmentHeadId);
            ViewBag.HeadFullName = String.Format("{0} {1}", manager.FirstName, manager.LastName);

            return View(_mapper.ConvertToViewModel(department));
        }
    }
}