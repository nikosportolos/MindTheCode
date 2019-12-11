using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;
using Timesheet.Models.ViewModels;

namespace Timesheet.Mappers
{
    public class ProjectMapper : IProjectMapper
    {
        public Project ConvertFromViewModel(ProjectViewModel viewModel)
        {
            return new Project
            {
                ID = viewModel.ID,
                Name = viewModel.Name                 
            };
        }

        public IEnumerable<Project> ConvertFromViewModels(IEnumerable<ProjectViewModel> viewModels)
        {
            List<Project> projects = new List<Project>();
            foreach(var v in viewModels)
            {
                projects.Add(ConvertFromViewModel(v));
            }

            return projects;
        }

        public ProjectViewModel ConvertToViewModel(Project project)
        {
            return new ProjectViewModel
            {
                ID = project.ID,
                Name = project.Name,
                DepartmentOwnerId = project.DepartmentOwner.ID                
            };
        }

        public IEnumerable<ProjectViewModel> ConvertToViewModels(IEnumerable<Project> projects)
        {
            List<ProjectViewModel> viewModels = new List<ProjectViewModel>();
            foreach (var p in projects)
            {
                viewModels.Add(ConvertToViewModel(p));
            }

            return viewModels;
        }
    }
}
