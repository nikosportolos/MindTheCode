using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;

namespace Timesheet.Models.ViewModels
{
    public class UserViewModel
    {
        public string Id { get; set; }
        public string FullName { get; set; }
        public string FirstName { get; set; }
        public string UserName { get; set; }
        public string LastName { get; set; }
        public string Email { get; set; }
        public int DepartmentId { get; set; }
        public string DepartmentName { get; set; }
        public double CostPerHour { get; set; }
        public string ManagerId { get; set; }
        public string ManagerName { get; set; }
        public Department Department { get; set; }
    }
}
