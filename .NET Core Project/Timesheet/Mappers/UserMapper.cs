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
            UserViewModel viewModel = new UserViewModel();
            viewModel.Id = user.Id;
            viewModel.CostPerHour = user.CostPerHour;

            if (user.Department != null)
                viewModel.DepartmentId = user.Department.Id;

            viewModel.Email = user.Email;
            viewModel.FirstName = user.FirstName;
            viewModel.LastName = user.LastName;

            if (user.Manager != null)
                viewModel.ManagerId = user.Manager.Id;

            return viewModel;
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
