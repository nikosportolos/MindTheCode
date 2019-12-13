using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;

namespace Timesheet.Models.ViewModels
{
    public class ProjectViewModel
    {
        public int Id { get; set; }
        public string Name { get; set; }
        public int DepartmentOwnerId { get; set; }
        public Department Department { get; set; }
        public string DepartmentName { get; set; }
    }
}
