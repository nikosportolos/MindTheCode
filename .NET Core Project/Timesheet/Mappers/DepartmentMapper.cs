using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;
using Timesheet.Models.ViewModels;

namespace Timesheet.Mappers
{
    public class DepartmentMapper : IDepartmentMapper
    {
        public Department ConvertFromViewModel(DepartmentViewModel viewModel, User manager)
        {
            Department department = new Department
            {
                Id = viewModel.Id,
                Name = viewModel.Name
            };

            if (manager != null)
            {
                department.DepartmentHead = manager;
                department.DepartmentHeadId = manager.Id;
            }

            return department;
        }

        public IEnumerable<Department> ConvertFromViewModels(Dictionary<DepartmentViewModel, User> viewModels)
        {
            List<Department> departments = new List<Department>();

            foreach (KeyValuePair<DepartmentViewModel, User> viewModel in viewModels)
            {
                departments.Add(ConvertFromViewModel(viewModel.Key, viewModel.Value));
            }

            return departments;
        }

        public DepartmentViewModel ConvertToViewModel(Department department)
        {
            DepartmentViewModel viewModel = new DepartmentViewModel
            {
                Id = department.Id,
                Name = department.Name,
            };

            if (department.DepartmentHead != null)
            {
                viewModel.DepartmentHeadId = department.DepartmentHead.Id;
                viewModel.UserFullName = department.DepartmentHead.FirstName + " " + department.DepartmentHead.LastName;
            }

            return viewModel;
        }

        public IEnumerable<DepartmentViewModel> ConvertToViewModels(IEnumerable<Department> departments)
        {
            List<DepartmentViewModel> viewModels = new List<DepartmentViewModel>();
            foreach (Department d in departments)
            {
                viewModels.Add(ConvertToViewModel(d));
            }

            return viewModels;
        }
    }
}
