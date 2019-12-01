using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Timesheet.Models
{
    public class DepartmentProject
    {
        public int DepartmentID { get; set; }
        public int ProjectID { get; set; }

        public Department Department { get; set; }
        public Project Project { get; set; }
    }
}
