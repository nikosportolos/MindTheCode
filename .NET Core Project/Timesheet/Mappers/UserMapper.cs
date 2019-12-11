using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;
using Timesheet.Models.ViewModels;

namespace Timesheet.Mappers
{
    public class UserMapper : IUserMapper
    {
        public User ConvertFromViewModel(UserViewModel viewModel)
        {
            return new User
            {

            };
        }

        public IEnumerable<User> ConvertFromViewModels(IEnumerable<UserViewModel> viewModels)
        {
            List<User> users = new List<User>();
            foreach (UserViewModel v in viewModels)
            {
                users.Add(ConvertFromViewModel(v));
            }

            return users;
        }

        public UserViewModel ConvertToViewModel(User user)
        {
            return new UserViewModel
            {
                Id = Int32.Parse(user.Id),
                CostPerHour = user.CostPerHour,
                DepartmentId = user.Department.ID,
                Email = user.Email,
                FirstName = user.FirstName,
                LastName = user.LastName,
                ManagerId = Int32.Parse(user.Manager.Id)
            };
        }

        public IEnumerable<UserViewModel> ConvertToViewModels(IEnumerable<User> users)
        {
            List<UserViewModel> viewModels = new List<UserViewModel>();
            foreach (User u in users)
            {
                viewModels.Add(ConvertToViewModel(u));
            }

            return viewModels;
        }
    }
}
