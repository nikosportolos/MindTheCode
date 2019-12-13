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
        public Department ConvertFromViewModel(DepartmentViewModel viewModel)
        {
            Department department = new Department
            {
                Id = viewModel.Id,
                Name = viewModel.Name,
                DepartmentHead = new User(),
                DepartmentHeadId = viewModel.DepartmentHeadId
            };

            return department;
        }

        public IEnumerable<Department> ConvertFromViewModels(IEnumerable<DepartmentViewModel> viewModels)
        {
            List<Department> departments = new List<Department>();
            foreach (DepartmentViewModel v in viewModels)
            {
                departments.Add(ConvertFromViewModel(v));
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
