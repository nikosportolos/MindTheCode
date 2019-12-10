using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;

namespace Timesheet.Data
{
    public class ProjectInitializer
    {
        public static void SeedProjects()
        {
            Project project = new Project
            {
                ID = 1,
                Name = "Customer support",
                DepartmentOwner = new Department
                {
                    ID = 1
                }
            };
        }
    }
}
