using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Linq;
using System.Threading.Tasks;

namespace Timesheet.Models
{
    public class Department
    {
        public int ID { get; set; }
        [NotNull]
        public string Name { get; set; }
        public User DepartmentHead { get; set; }

        public ICollection<DepartmentProject> DepartmentProjects { get; set; }
    }
}
