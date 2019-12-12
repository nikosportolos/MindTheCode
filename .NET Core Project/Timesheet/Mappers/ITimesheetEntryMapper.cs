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
        public Task<TimesheetEntry> ConvertFromViewModel(TimesheetEntryViewModel viewModel);
        public Task<IEnumerable<TimesheetEntry>> ConvertFromViewModels(IEnumerable<TimesheetEntryViewModel> viewModels);

        public Task<TimesheetEntryViewModel> ConvertToViewModel(TimesheetEntry entry);
        public Task<IEnumerable<TimesheetEntryViewModel>> ConvertToViewModels(IEnumerable<TimesheetEntry> entries);
    }
}