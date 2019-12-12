using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;

namespace Timesheet.Models.ViewModels
{
    public class DepartmentViewModel
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public string DepartmentHeadId { get; set; }
        public string UserFullName { get; set; }
    }
}
