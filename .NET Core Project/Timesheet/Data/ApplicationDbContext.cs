using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using Timesheet.Models.Entities;

namespace Timesheet.Data
{
    public class ApplicationDbContext : IdentityDbContext<User>
    {
        #region Db Sets
        public DbSet<Project> Projects { get; set; }
        public DbSet<TimesheetEntry> TimesheetEntries { get; set; }
        public DbSet<Department> Departments { get; set; }
        public override DbSet<User> Users { get; set; }
        #endregion Db Sets

        public ApplicationDbContext(DbContextOptions<ApplicationDbContext> options) : base(options)
        {

        }

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            base.OnModelCreating(modelBuilder);

            /** User **/
            modelBuilder.Entity<User>()
                .HasKey(u => u.Id);

            /** Department **/
            modelBuilder.Entity<Department>()
                .HasKey(d => d.ID);

            modelBuilder.Entity<Department>()
                .HasOne(d => d.DepartmentHead)
                .WithOne(u => u.Department)
                .HasForeignKey<Department>(ad => ad.DepartmentHeadId);

            /** TimesheetEntry **/
            modelBuilder.Entity<TimesheetEntry>()
                .HasKey(t => t.ID);

            modelBuilder.Entity<TimesheetEntry>()
                .HasOne(t => t.User);

            /** Project **/
            modelBuilder.Entity<Project>()
                .HasKey(p => p.ID);

            /** DepartmentProject **/
            modelBuilder.Entity<DepartmentProject>()
                .HasKey(dp => new { dp.DepartmentID, dp.ProjectID });

            modelBuilder.Entity<DepartmentProject>()
                .HasOne(dp => dp.Department)
                .WithMany(d => d.DepartmentProjects)
                .HasForeignKey(dp => dp.DepartmentID);

            modelBuilder.Entity<DepartmentProject>()
                .HasOne(dp => dp.Project)
                .WithMany(p => p.DepartmentProjects)
                .HasForeignKey(dp => dp.ProjectID);


            modelBuilder.Entity<IdentityRole>().HasData(
                new IdentityRole() { Name = "Employee", NormalizedName = "EMPLOYEE" },
                new IdentityRole() { Name = "Manager", NormalizedName = "MANAGER" },
                new IdentityRole() { Name = "Administrator", NormalizedName = "ADMINISTRATOR" });

        }

    }
}
