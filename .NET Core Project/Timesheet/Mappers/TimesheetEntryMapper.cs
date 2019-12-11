using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;
using Timesheet.Models.ViewModels;

namespace Timesheet.Mappers
{
    public class TimesheetEntryMapper : ITimesheetEntryMapper
    {

        public TimesheetEntry ConvertFromViewModel(TimesheetEntryViewModel viewModel)
        {
            return new TimesheetEntry
            {
                ID = viewModel.ID,
                //UserID = viewModel.UserID,
                //ProjectID = viewModel.Project.ID;,
                EntryDate = viewModel.EntryDate,
                HoursWorked = viewModel.HoursWorked
            };
        }

        public IEnumerable<TimesheetEntry> ConvertFromViewModels(IEnumerable<TimesheetEntryViewModel> viewModels)
        {
            List<TimesheetEntry> list = new List<TimesheetEntry>();
            foreach (TimesheetEntryViewModel viewModel in viewModels)
            {
                list.Add(ConvertFromViewModel(viewModel));
            }

            return list;
        }

        public TimesheetEntryViewModel ConvertToViewModel(TimesheetEntry entry)
        {
            TimesheetEntryViewModel viewModel = new TimesheetEntryViewModel();
            if (entry != null)
            {
                viewModel.ID = entry.ID;
                viewModel.EntryDate = entry.EntryDate;
                viewModel.HoursWorked = entry.HoursWorked;
                if (entry.Project != null)
                    viewModel.ProjectID = entry.Project.ID;
                if (entry.User != null)
                    viewModel.UserID = Int32.Parse(entry.User.Id);
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
