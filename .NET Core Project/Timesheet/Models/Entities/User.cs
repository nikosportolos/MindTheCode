using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Linq;
using System.Threading.Tasks;

namespace Timesheet.Models.Entities
{
    public class User : IdentityUser
    {
        [PersonalData]
        [NotNull]
        public string FirstName { get; set; }

        [PersonalData]
        [NotNull]
        public string LastName { get; set; }

        public virtual Department Department { get; set; }
        public int DepartmentId { get; set; }
        public double CostPerHour { get; set; }
        public virtual User Manager { get; set; }
        public string ManagerId { get; set; }

    }
}
