using Microsoft.CodeAnalysis;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models;
using Timesheet.Models.Entities;

namespace Timesheet.Repositories
{
    public interface ITimesheetEntryRepository : IRepository<TimesheetEntry>
    {
        List<Models.Entities.Project> GetAllProjects();
    }
}
