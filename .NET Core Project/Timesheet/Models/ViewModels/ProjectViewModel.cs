using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Timesheet.Models.ViewModels
{
    public class ProjectViewModel
    {
        public int ID { get; set; }
        public string Name { get; set; }
        public int DepartmentOwnerID { get; set; }
    }
}
