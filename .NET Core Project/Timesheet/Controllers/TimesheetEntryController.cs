using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
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
        private readonly ITimesheetEntryRepository _repository;
        private readonly ITimesheetEntryMapper _mapper;

        public TimesheetEntryController([FromServices] ITimesheetEntryRepository repository, ITimesheetEntryMapper mapper)
        {
            _repository = repository;
            _mapper = mapper;
        }

        [HttpGet]
        public IActionResult Index()
        {
            List<TimesheetEntryViewModel> viewModels = _mapper.ConvertToViewModels(_repository.GetAll()).ToList();
            return View(viewModels);
        }

        [HttpGet]
        public IActionResult Create()
        {
            ViewBag.Projects = new SelectList(_repository.GetAllProjects(), "ID", "Name");
            return View();
        }

        [HttpPost]
        public async Task<IActionResult> Create(TimesheetEntryViewModel viewModel)
        {
            await _repository.Create(_mapper.ConvertFromViewModel(viewModel));
            return RedirectToAction("Index", "TimesheetEntry");
        }

        public async Task<IActionResult> Details(int id)
        {
            TimesheetEntry entry = await _repository.GetById(id);
            return View(_mapper.ConvertToViewModel((entry)));
        }

        public async Task<IActionResult> Edit(int id)
        {
            TimesheetEntry entry = await _repository.GetById(id);
            return View(_mapper.ConvertToViewModel((entry)));
        }

        [HttpPost]
        public async Task<IActionResult> Edit(TimesheetEntryViewModel viewModel)
        {
            TimesheetEntry entry = _mapper.ConvertFromViewModel(viewModel);
            await _repository.Update(entry);
            return RedirectToAction("Details", "TimesheetEntry", new { id = entry.ID });

        }

        public async Task<IActionResult> Delete(int id)
        {
            TimesheetEntry entry = await _repository.GetById(id);
            return View(_mapper.ConvertToViewModel((entry)));
        }

        [HttpPost]
        public async Task<IActionResult> Delete(TimesheetEntryViewModel viewModel)
        {
            TimesheetEntry entry = _mapper.ConvertFromViewModel(viewModel);
            await _repository.Delete(entry.ID);
            return RedirectToAction("Index", "TimesheetEntry");
        }

    }
}
