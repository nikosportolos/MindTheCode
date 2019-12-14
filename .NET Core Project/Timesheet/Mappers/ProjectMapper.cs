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
        public Project ConvertFromViewModel(ProjectViewModel viewModel, Department departmentOwner)
        {
            Project project = new Project
            {
                Id = viewModel.Id,
                Name = viewModel.Name,
            };

            if (departmentOwner != null)
            {
                project.DepartmentOwner = departmentOwner;
            }

            return project;
        }

        public IEnumerable<Project> ConvertFromViewModels(Dictionary<ProjectViewModel, Department> viewModels)
        {
            List<Project> projects = new List<Project>();

            foreach (KeyValuePair<ProjectViewModel, Department> viewModel in viewModels)
            {
                projects.Add(ConvertFromViewModel(viewModel.Key, viewModel.Value));
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
            {
                viewModel.DepartmentOwnerId = project.DepartmentOwner.Id;
                viewModel.DepartmentName = project.DepartmentOwner.Name;
            }          

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
