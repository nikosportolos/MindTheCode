using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Timesheet.Models
{
    public class Project
    {
        public int ID { get; set; }
        public string Name { get; set; }
        public List<Department> Departments { get; set; }
        public Department DepartmentOwner { get; set; }

    }
}
