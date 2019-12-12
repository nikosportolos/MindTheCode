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

        public List<TimesheetEntry> GetTimesheetEntriesForEmployee(User user)
        {
            return _dbContext.TimesheetEntries.Where(e => e.User.Id == user.Id).ToList();
        }

        public List<TimesheetEntry> GetTimesheetEntriesForManager(User user)
        {
            return _dbContext.TimesheetEntries.Where(e => e.User.Department.Id == user.Department.Id).ToList();
        }
    }
}