using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;

namespace Timesheet.Data
{
    public class MockDataInitializer
    {
        private static User user1, user2, user3;
        private static Department department1, department2;
        private static Project project1, project2, project3, project4;

        public static void SeedMockData(UserManager<User> userManager, ApplicationDbContext dbContext)
        {
            SeedDepartments(dbContext);
            SeedUsers(userManager, dbContext);
            //SeedProjects(dbContext);
        }
        private static void SeedDepartments(ApplicationDbContext dbContext)
        {
            department1 = new Department
            {
                Name = "Application Management",
                //DepartmentHead = user3,
            };

            var res = dbContext.Departments.Where(d => d.Name == department1.Name).FirstOrDefault();
            if (res == null)
                dbContext.Departments.Add(department1);

            department2 = new Department
            {
                Name = "IT",
                //DepartmentHead = user3,
            };

            res = dbContext.Departments.Where(d => d.Name == department2.Name).FirstOrDefault();
            if (res == null)
                dbContext.Departments.Add(department2);

            // Save changes to database
            dbContext.SaveChanges();
        }

        private static void SeedProjects(ApplicationDbContext dbContext)
        {
            project1 = new Project
            {
                Name = "Customer support",
                DepartmentOwner = department1
            };
            dbContext.Projects.Add(project1);
            dbContext.SaveChanges();
        }

        private static void SeedUsers(UserManager<User> userManager, ApplicationDbContext dbContext)
        {
            // Create administrator
            if (userManager.FindByEmailAsync("nportolos@timesheet.com").Result == null)
            {
                user1 = new User()
                {
                    FirstName = "Nikos",
                    LastName = "Portolos",
                    UserName = "nportolos",
                    Email = "nportolos@timesheet.com",
                    EmailConfirmed = true,
                    CostPerHour = 120.0,
                    DepartmentId = department1.Id
                };
                AddRoleToUser(user1, "Administrator", "123123", userManager);
            }

            if (userManager.FindByEmailAsync("bkontos@hotmail.com").Result == null)
            {
                // Create manager
                user2 = new User()
                {
                    FirstName = "Babis",
                    LastName = "Kontos",
                    UserName = "bkontos",
                    Email = "bkontos@hotmail.com",
                    EmailConfirmed = false,
                    DepartmentId = department1.Id
                };
                AddRoleToUser(user2, "Manager", "123123", userManager);
            }

            // Create employee
            if (userManager.FindByEmailAsync("fvkontos@hotmail.com").Result == null)
            {
                user3 = new User()
                {
                    FirstName = "Philip",
                    LastName = "Kontos",
                    UserName = "fvkontos",
                    Email = "fvkontos@hotmail.com",
                    EmailConfirmed = true,
                    CostPerHour = 80,
                    DepartmentId = department1.Id
                };
                AddRoleToUser(user3, "Employee", "123123", userManager);
            }
        }

        private static void AddRoleToUser(User user, string role, string password, UserManager<User> userManager)
        {
            IdentityResult identityResult2 = userManager.CreateAsync(user, password).Result;

            if (identityResult2.Succeeded)
            {
                userManager.AddToRoleAsync(user, role).Wait();
            }
        }
    }
}
