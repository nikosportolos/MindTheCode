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
            return new Department
            {
                ID = viewModel.ID,
                Name = viewModel.Name,
                DepartmentHeadId = viewModel.DepartmentHeadId
            };
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
            return new DepartmentViewModel
            {
                ID = department.ID,
                Name = department.Name,
                DepartmentHeadId = department.DepartmentHeadId
            };
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
