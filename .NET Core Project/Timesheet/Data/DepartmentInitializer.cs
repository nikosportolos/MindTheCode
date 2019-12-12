using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;

namespace Timesheet.Data
{
    public class DepartmentInitializer
    {
        public static void SeedDepartments()
        {
            Department department = new Department 
            {
                ID = 1,
                Name = "Application Management",
                DepartmentHeadId = 2,
                DepartmentHead = new User
                {
                    FirstName = "Dimitra",
                    LastName = "Fountzoula"
                }
            };
        }
    }
}
