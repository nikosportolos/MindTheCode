using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Timesheet.Models
{
    public class Department
    {
        public int ID { get; set; }
        public string Name { get; set; }
        public User DepartmentHead { get; set; }
    }
}
