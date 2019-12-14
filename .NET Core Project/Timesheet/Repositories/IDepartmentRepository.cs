using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;

namespace Timesheet.Repositories
{
    public interface IDepartmentRepository : IRepository<Department>
    {
        public List<Department> GetDepartmentForUser(User user);
    }
}
