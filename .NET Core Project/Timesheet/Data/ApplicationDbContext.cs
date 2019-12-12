using System;
using System.Collections.Generic;
using System.Text;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Identity.EntityFrameworkCore;
using Microsoft.EntityFrameworkCore;
using Timesheet.Models.Entities;
using Timesheet.Models.ViewModels;

namespace Timesheet.Data
{
    public class ApplicationDbContext : IdentityDbContext<User>
    {
        #region Db Sets
        public DbSet<Project> Projects { get; set; }
        public DbSet<TimesheetEntry> TimesheetEntries { get; set; }
        public DbSet<Department> Departments { get; set; }
        public DbSet<User> Users { get; set; }

        public DbSet<UserViewModel> UserViewModel { get; set; }
        public DbSet<DepartmentViewModel> DepartmentViewModel { get; set; }
        public DbSet<ProjectViewModel> ProjectViewModel { get; set; }
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
                .HasKey(d => d.Id);

            modelBuilder.Entity<Department>()
                .HasOne(d => d.DepartmentHead)
                .WithOne(u => u.Department)
                .HasForeignKey<Department>(ad => ad.DepartmentHeadId);

            /** TimesheetEntry **/
            modelBuilder.Entity<TimesheetEntry>()
                .HasKey(t => t.Id);

            modelBuilder.Entity<TimesheetEntry>()
                .HasOne(t => t.User);

            /** Project **/
            modelBuilder.Entity<Project>()
                .HasKey(p => p.Id);

            /** DepartmentProject **/
            modelBuilder.Entity<DepartmentProject>()
                .HasKey(dp => new { dp.DepartmentId, dp.ProjectId });

            modelBuilder.Entity<DepartmentProject>()
                .HasOne(dp => dp.Department)
                .WithMany(d => d.DepartmentProjects)
                .HasForeignKey(dp => dp.DepartmentId);

            modelBuilder.Entity<DepartmentProject>()
                .HasOne(dp => dp.Project)
                .WithMany(p => p.DepartmentProjects)
                .HasForeignKey(dp => dp.ProjectId);


            modelBuilder.Entity<IdentityRole>().HasData(
                new IdentityRole() { Name = "Employee", NormalizedName = "EMPLOYEE" },
                new IdentityRole() { Name = "Manager", NormalizedName = "MANAGER" },
                new IdentityRole() { Name = "Administrator", NormalizedName = "ADMINISTRATOR" });

        }

    }
}