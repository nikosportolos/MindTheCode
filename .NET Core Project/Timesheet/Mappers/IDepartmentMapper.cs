using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;
using Timesheet.Models.ViewModels;

namespace Timesheet.Mappers
{
    public interface IDepartmentMapper
    {
        public Department ConvertFromViewModel(DepartmentViewModel viewModel);
        public IEnumerable<Department> ConvertFromViewModels(IEnumerable<DepartmentViewModel> viewModels);

        public DepartmentViewModel ConvertToViewModel(Department department);
        public IEnumerable<DepartmentViewModel> ConvertToViewModels(IEnumerable<Department> departments);
    }
}
