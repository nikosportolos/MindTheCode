using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;

namespace Timesheet
{
    public interface IRepository<TEntity> : IDisposable where TEntity : class
    {
        IEnumerable<TEntity> GetAll();
        Task<TEntity> GetById(dynamic id);
        Task Create(TEntity entity);
        Task Update(TEntity entity);
        Task Delete(dynamic id);
        Task Save();
    }
}
