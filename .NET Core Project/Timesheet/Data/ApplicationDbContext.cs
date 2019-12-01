using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using Timesheet.Models;

namespace Timesheet.Data
{
    public class ApplicationDbContext : IdentityDbContext
    {
        public DbSet<Project> Projects { get; set; }
        public DbSet<TimesheetEntry> TimesheetEntries { get; set; }
        public DbSet<Department> Departments { get; set; }
        public DbSet<User> Users { get; set; }
        public DbSet<Role> Roles { get; set; }

        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
        {

        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);


            modelBuilder.Entity<User>()
                .HasKey(u => u.ID);

            modelBuilder.Entity<Role>()
                .HasKey(r => r.ID);

            modelBuilder.Entity<Department>()
                .HasKey(d => d.ID);

            modelBuilder.Entity<TimesheetEntry>()
                .HasOne(t => t.User);
            //.WithOne(u=> );

            modelBuilder.Entity<Project>()
                .HasKey(p => p.ID);

            modelBuilder.Entity<Project>()
                .HasMany(p => p.Departments);
                
        }
    }
}
