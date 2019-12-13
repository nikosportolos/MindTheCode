using Microsoft.EntityFrameworkCore;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Data;

namespace Timesheet
{
    public class Repository<TEntity> : IRepository<TEntity> where TEntity : class
    {
        private readonly ApplicationDbContext _dbContext = null;
        private readonly DbSet<TEntity> table = null;

        public Repository(ApplicationDbContext dbContext)
        {
            _dbContext = dbContext;
            table = _dbContext.Set<TEntity>();
        }

        #region Implement IRepository

        public async Task<IEnumerable<TEntity>> GetAll()
        {
            return await table.AsNoTracking().ToListAsync<TEntity>();
        }

        public async Task<TEntity> GetByGuid(string guid)
        {
            return await table.FindAsync(guid);
        }

        public async Task<TEntity> GetById(int id)
        {
            return await table.FindAsync(id);
        }

        public async Task Create(TEntity entity)
        {
            await table.AddAsync(entity);
            await Save();
        }

        public async Task Update(TEntity entity)
        {
            table.Update(entity);
            await Save();
        }

        public async Task Delete(dynamic id)
        {
            var entity = await GetById(id);
            table.Remove(entity);
            await Save();
        }

        public async Task DeleteByGuid(dynamic id)
        {
            var entity = await GetByGuid(id);
            table.Remove(entity);
            await Save();
        }

        public async Task Save()
        {
            await _dbContext.SaveChangesAsync();
        }

        #endregion Implement IRepository

        #region Implement IDisposable
        private bool disposed = false;

        protected virtual void Dispose(bool disposing)
        {
            if (!this.disposed)
            {
                if (disposing)
                {
                    _dbContext.Dispose();
                }
            }
            this.disposed = true;
        }

        public void Dispose()
        {
            Dispose(true);
            GC.SuppressFinalize(this);
        }
        #endregion Implement IRepository
    }
}