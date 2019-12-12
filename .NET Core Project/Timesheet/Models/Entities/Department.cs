using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Linq;
using System.Threading.Tasks;

namespace Timesheet.Models.Entities
{
    public class Department
    {
        public int Id { get; set; }

        [NotNull]
        public string Name { get; set; }

        public string DepartmentHeadId { get; set; }

        public User DepartmentHead { get; set; }

        public ICollection<DepartmentProject> DepartmentProjects { get; set; }

        public ICollection<User> DepartmentUsers { get; set; }

    }
}
