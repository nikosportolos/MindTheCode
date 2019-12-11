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

        public static void SeedDefaultUsers(UserManager<User> defaultSimpleUser)
        {
            User defaultUser2 = new User()
            {
                FirstName = "Babis",
                LastName = "Kontos",
                UserName = "bkontos",
                Email = "bkontos@hotmail.com",
                EmailConfirmed = false
            };

            IdentityResult identityResult2 = defaultSimpleUser.CreateAsync(defaultUser2, "1234").Result;

            if (identityResult2.Succeeded)
            {
                defaultSimpleUser.AddToRoleAsync(defaultUser2, "SimpleUser").Wait();
            }

            User defaultUser1 = new User()
            {
                FirstName = "Philip",
                LastName = "Kontos",
                UserName = "fvkontos",
                Email = "fvkontos@hotmail.com",
                EmailConfirmed = false
            };

            IdentityResult identityResult1 = defaultSimpleUser.CreateAsync(defaultUser1, "1234").Result;

            if(identityResult2.Succeeded)
            {
                defaultSimpleUser.AddToRoleAsync(defaultUser1, "SimpleUser").Wait();
            }
        }
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
