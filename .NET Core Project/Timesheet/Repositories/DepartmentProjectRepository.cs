using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Data;
using Timesheet.Models.Entities;

namespace Timesheet.Repositories
{
    public class DepartmentProjectRepository : Repository<DepartmentProject>, IDepartmentProjectRepository
    {
        private readonly ApplicationDbContext _dbContext;
        public DepartmentProjectRepository(ApplicationDbContext dbContext) : base(dbContext)
        {
            _dbContext = dbContext;
        }
    }
}