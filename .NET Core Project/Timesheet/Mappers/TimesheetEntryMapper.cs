using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Data;
using Timesheet.Models.Entities;
using Timesheet.Models.ViewModels;
using Timesheet.Repositories;

namespace Timesheet.Mappers
{
    public class TimesheetEntryMapper : ITimesheetEntryMapper
    {
        public TimesheetEntry ConvertFromViewModel(TimesheetEntryViewModel viewModel, Project project)
        {
            TimesheetEntry entry = new TimesheetEntry
            {
                Id = viewModel.Id,
                //User = await _dbContext.FindAsync<TimesheetEntry>(viewModel.UserId),
                //Project = await _projectRepository.GetById(viewModel.ProjectId),
                EntryDate = viewModel.EntryDate,
                HoursWorked = viewModel.HoursWorked
            };

            if (project != null)
            {
                entry.Project = project;
            }

            return entry;
        }

        public IEnumerable<TimesheetEntry> ConvertFromViewModels(Dictionary<TimesheetEntryViewModel, Project> viewModels)
        {
            List<TimesheetEntry> list = new List<TimesheetEntry>();
            foreach (KeyValuePair<TimesheetEntryViewModel, Project> viewModel in viewModels)
            {
                list.Add(ConvertFromViewModel(viewModel.Key, viewModel.Value));
            }

            return list;
        }

        public TimesheetEntryViewModel ConvertToViewModel(TimesheetEntry entry)
        {
            TimesheetEntryViewModel viewModel = new TimesheetEntryViewModel();
            if (entry != null)
            {
                viewModel.Id = entry.Id;
                viewModel.EntryDate = entry.EntryDate;
                viewModel.HoursWorked = entry.HoursWorked;
                viewModel.ProjectId = entry.ProjectId;
                if (entry.User != null)
                    viewModel.UserId = entry.User.Id;
            }

            return viewModel;
        }

        public IEnumerable<TimesheetEntryViewModel> ConvertToViewModels(IEnumerable<TimesheetEntry> entries)
        {
            List<TimesheetEntryViewModel> list = new List<TimesheetEntryViewModel>();
            foreach (TimesheetEntry entry in entries)
            {
                list.Add(ConvertToViewModel(entry));
            }

            return list;
        }

    }
}
