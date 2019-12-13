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
                Id = viewModel.Id,
                Name = viewModel.Name,
                DepartmentOwner = viewModel.Department
            };
        }

        public IEnumerable<Project> ConvertFromViewModels(IEnumerable<ProjectViewModel> viewModels)
        {
            List<Project> projects = new List<Project>();
            foreach (var v in viewModels)
            {
                projects.Add(ConvertFromViewModel(v));
            }

            return projects;
        }

        public ProjectViewModel ConvertToViewModel(Project project)
        {
            ProjectViewModel viewModel = new ProjectViewModel
            {
                Id = project.Id,
                Name = project.Name                 
            };

            if (project.DepartmentOwner != null)
                viewModel.DepartmentOwnerId = project.DepartmentOwner.Id;

            return viewModel;
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
