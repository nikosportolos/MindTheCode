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
        //private readonly IUserRepository _userRepository;
        //private readonly IProjectRepository _projectRepository;
        private readonly ApplicationDbContext _dbContext;

        public TimesheetEntryMapper(ApplicationDbContext dbContext)
        {
            _dbContext = dbContext;
        }

        public async Task<TimesheetEntry> ConvertFromViewModel(TimesheetEntryViewModel viewModel)
        {
            return new TimesheetEntry
            {
                Id = viewModel.Id,
                //User = await _dbContext.FindAsync<TimesheetEntry>(viewModel.UserId),
                //Project = await _projectRepository.GetById(viewModel.ProjectId),
                EntryDate = viewModel.EntryDate,
                HoursWorked = viewModel.HoursWorked
            };
        }

        public async Task<IEnumerable<TimesheetEntry>> ConvertFromViewModels(IEnumerable<TimesheetEntryViewModel> viewModels)
        {
            List<TimesheetEntry> list = new List<TimesheetEntry>();
            foreach (TimesheetEntryViewModel viewModel in viewModels)
            {
                list.Add(await ConvertFromViewModel(viewModel));
            }

            return list;
        }

        public async Task<TimesheetEntryViewModel> ConvertToViewModel(TimesheetEntry entry)
        {
            TimesheetEntryViewModel viewModel = new TimesheetEntryViewModel();
            if (entry != null)
            {
                viewModel.Id = entry.Id;
                viewModel.EntryDate = entry.EntryDate;
                viewModel.HoursWorked = entry.HoursWorked;
                if (entry.Project != null)
                    viewModel.ProjectId = entry.Project.Id;
                if (entry.User != null)
                    viewModel.UserId = entry.User.Id;
            }

            return viewModel;
        }

        public async Task<IEnumerable<TimesheetEntryViewModel>> ConvertToViewModels(IEnumerable<TimesheetEntry> entries)
        {
            List<TimesheetEntryViewModel> list = new List<TimesheetEntryViewModel>();
            foreach (TimesheetEntry entry in entries)
            {
                list.Add(await ConvertToViewModel(entry));
            }

            return list;
        }

    }
}
