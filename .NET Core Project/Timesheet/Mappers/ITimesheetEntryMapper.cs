using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;
using Timesheet.Models.ViewModels;

namespace Timesheet.Mappers
{
    public interface ITimesheetEntryMapper
    {
        public TimesheetEntry ConvertFromViewModel(TimesheetEntryViewModel viewModel);
        public IEnumerable<TimesheetEntry> ConvertFromViewModels(IEnumerable<TimesheetEntryViewModel> viewModels);

        public TimesheetEntryViewModel ConvertToViewModel(TimesheetEntry entry);
        public IEnumerable<TimesheetEntryViewModel> ConvertToViewModels(IEnumerable<TimesheetEntry> entries);
    }
}