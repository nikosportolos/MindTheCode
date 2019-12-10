using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;
using Timesheet.Models.ViewModels;

namespace Timesheet.Mappers
{
    public interface IProjectMapper
    {
        public Project ConvertFromViewModel(ProjectViewModel viewModel);
        public IEnumerable<Project> ConvertFromViewModels(IEnumerable<ProjectViewModel> viewModels);

        public ProjectViewModel ConvertToViewModel(Project project);
        public IEnumerable<ProjectViewModel> ConvertToViewModels(IEnumerable<Project> projects);
    }
}
