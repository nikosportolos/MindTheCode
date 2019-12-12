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

        public static void SeedMockData(UserManager<User> userManager)
        {
            SeedDepartments();
            SeedProjects();
            SeedUsers(userManager);
        }
        private static void SeedDepartments()
        {
            department1 = new Department
            {
                Name = "Application Management",
                DepartmentHead = user3 ,                
            };

            department2 = new Department
            {
                Name = "IT",
                DepartmentHead = user3,
            };
        }

        private static void SeedProjects()
        {
            project1 = new Project
            {
                Name = "Customer support",
                DepartmentOwner = department1                
            };
        }

        private static void SeedUsers(UserManager<User> userManager)
        {
            // Create administrator
            user1 = new User()
            {
                FirstName = "Nikos",
                LastName = "Portolos",
                UserName = "nportolos",
                Email = "nportolos@timesheet.com",
                EmailConfirmed = true,
                CostPerHour = 120.0,
                Department = department1
            };
            AddRoleToUser(user1, "Administrator", "123", userManager);

            // Create manager
            user2 = new User()
            {
                FirstName = "Babis",
                LastName = "Kontos",
                UserName = "bkontos",
                Email = "bkontos@hotmail.com",
                EmailConfirmed = false,
                Department = department2
            };
            AddRoleToUser(user2, "Manager", "123", userManager);

            // Create employee
            user3 = new User()
            {
                FirstName = "Philip",
                LastName = "Kontos",
                UserName = "fvkontos",
                Email = "fvkontos@hotmail.com",
                EmailConfirmed = true,
                CostPerHour = 80,
                Manager = user2,
                Department = department2                
            };
            AddRoleToUser(user3, "Employee", "123", userManager);
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
