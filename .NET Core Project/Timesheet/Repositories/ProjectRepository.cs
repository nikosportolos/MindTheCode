using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Data;
using Timesheet.Models.Entities;

namespace Timesheet.Repositories
{
    public class ProjectRepository : Repository<Project>, IProjectRepository
    {
        private readonly ApplicationDbContext _dbContext;
        public ProjectRepository(ApplicationDbContext dbContext) : base(dbContext)
        {
            _dbContext = dbContext;
        }

        public List<Project> GetProjectsForDepartment(Department department)
        {
            return _dbContext.Projects.Where(e => e.DepartmentOwnerId == department.Id).ToList();
        }
    }
}