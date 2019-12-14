using System;
using System.Collections.Generic;
using System.ComponentModel.DataAnnotations.Schema;
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
        
        public virtual User DepartmentHead { get; set; }
        public string DepartmentHeadId { get; set; }

        public ICollection<DepartmentProject> Projects { get; set; }
        //public ICollection<Project> OwnedProjects { get; set; }
        public ICollection<User> DepartmentUsers { get; set; }

    }
}
