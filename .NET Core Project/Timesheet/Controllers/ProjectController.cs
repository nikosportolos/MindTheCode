using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Timesheet.Mappers;
using Timesheet.Models.Entities;
using Timesheet.Models.ViewModels;
using Timesheet.Repositories;

namespace Timesheet.Controllers
{
    public class ProjectController : Controller
    {
        private readonly IProjectRepository _repository;
        private readonly UserManager<User> _userManager;
        private readonly IProjectMapper _mapper;

        public ProjectController([FromServices] IProjectRepository repository, IProjectMapper mapper, UserManager<User> userManager)
        {
            _repository = repository;
            _mapper = mapper;
            _userManager = userManager;
        }

        // GET: Project
        public ActionResult Index()
        {
            List<Project> projects = _repository.GetAll().ToList();
            return View(_mapper.ConvertToViewModels(projects));
        }

        // GET: Project/Details/5
        public async Task<IActionResult> Details(int id)
        {
            Project project = await _repository.GetById(id);
            return View(_mapper.ConvertToViewModel((project)));
        }

        // GET: Project/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: Project/Create
        [HttpPost]
        public async Task<IActionResult> Create(ProjectViewModel viewModel)
        {
            try
            {
                await _repository.Create(_mapper.ConvertFromViewModel(viewModel));
                return RedirectToAction(nameof(Index));
            }
            catch
            {
                return View();
            }
        }

        // GET: Project/Edit/5
        public async Task<IActionResult> Edit(int id)
        {
            Project project = await _repository.GetById(id);
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
                await _repository.Update(project);
                return RedirectToAction(nameof(Details), new { id = project.ID });
            }
            catch
            {
                return View();
            }
        }

        // GET: Project/Delete/5
        public async Task<IActionResult> Delete(int id)
        {
            Project project = await _repository.GetById(id.ToString());
            return View(_mapper.ConvertToViewModel((project)));
        }
        
        [HttpPost]
        public async Task<IActionResult> Delete(ProjectViewModel viewModel)
        {
            Project project = _mapper.ConvertFromViewModel(viewModel);
            await _repository.Delete(project.ID);
            return RedirectToAction(nameof(Index));
        }
    }
}