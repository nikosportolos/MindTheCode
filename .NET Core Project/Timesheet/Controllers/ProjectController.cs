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
    public class ProjectController : Controller
    {
        private readonly IProjectRepository _projectRepository;
        private readonly IDepartmentRepository _departmentRepository;
        private readonly UserManager<User> _userManager;
        private readonly IProjectMapper _mapper;

        public ProjectController([FromServices] IProjectRepository repository, IProjectMapper mapper, UserManager<User> userManager, IDepartmentRepository departmentRepository)
        {
            _projectRepository = repository;
            _departmentRepository = departmentRepository;
            _mapper = mapper;
            _userManager = userManager;
        }

        // GET: Project
        public async Task<IActionResult> Index()
        {
            List<Project> projects = (await _projectRepository.GetAll()).ToList();
            return View(_mapper.ConvertToViewModels(projects));
        }

        // GET: Project/Details/5
        public async Task<IActionResult> Details(int id)
        {
            Project project = await _projectRepository.GetById(id);
            return View(_mapper.ConvertToViewModel((project)));
        }

        // GET: Project/Create
        public async Task<IActionResult> Create()
        {
            ViewBag.Departments = new SelectList(await _departmentRepository.GetAll(), "Id", "Name");
            return View();
        }

        // POST: Project/Create
        [HttpPost]
        public async Task<IActionResult> Create(ProjectViewModel viewModel)
        {
            await _projectRepository.Create(_mapper.ConvertFromViewModel(viewModel));
            return RedirectToAction(nameof(Index));
        }

        // GET: Project/Edit/5
        public async Task<IActionResult> Edit(int id)
        {
            Project project = await _projectRepository.GetById(id);
            return View(_mapper.ConvertToViewModel((project)));
        }

        // POST: Project/Edit/5
        [HttpPost]
        [ValidateAntiForgeryToken]
        public async Task<IActionResult> Edit(ProjectViewModel viewModel)
        {
            try
            {
                Project project = _mapper.ConvertFromViewModel(viewModel);
                await _projectRepository.Update(project);
                return RedirectToAction(nameof(Details), new { id = project.Id });
            }
            catch
            {
                return View();
            }
        }

        // GET: Project/Delete/5
        public async Task<IActionResult> Delete(int id)
        {
            Project project = await _projectRepository.GetById(id);
            return View(_mapper.ConvertToViewModel((project)));
        }

        [HttpPost]
        public async Task<IActionResult> Delete(ProjectViewModel viewModel)
        {
            Project project = _mapper.ConvertFromViewModel(viewModel);
            await _projectRepository.Delete(project.Id);
            return RedirectToAction(nameof(Index));
        }
    }
}