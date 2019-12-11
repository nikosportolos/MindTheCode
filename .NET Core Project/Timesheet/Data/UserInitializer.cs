using Microsoft.AspNetCore.Identity;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;

namespace Timesheet.Data
{
    public class UserInitializer
    {
        public static void SeedUsers(UserManager<User> userManager)
        {
            User user = new User()
            {
                FirstName = "Nikos",
                LastName = "Portolos",
                UserName = "nportolos",
                Email = "nportolos@timesheet.com",
                EmailConfirmed = true
            };

            IdentityResult result = userManager.CreateAsync(user, "123").Result;

            if (result.Succeeded)
            {
                userManager.AddToRoleAsync(user, "Admin").Wait();
            }
        }
    }
}
