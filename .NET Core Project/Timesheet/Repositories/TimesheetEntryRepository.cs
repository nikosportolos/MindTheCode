using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Data;
using Timesheet.Models.Entities;

namespace Timesheet.Repositories
{
    public class TimesheetEntryRepository : Repository<TimesheetEntry>, ITimesheetEntryRepository
    {
        private readonly ApplicationDbContext _dbContext;
        public TimesheetEntryRepository(ApplicationDbContext dbContext) : base(dbContext)
        {
            _dbContext = dbContext;
        }

        public List<Project> GetAllProjects()
        {
            return _dbContext.Projects.ToList();
        }
    }
}