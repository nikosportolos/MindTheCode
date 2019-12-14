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
        public User ConvertFromViewModel(UserViewModel viewModel, Department department)
        {
            User user = new User
            {
                UserName = viewModel.UserName,
                Email = viewModel.Email,
                EmailConfirmed = true,
                FirstName = viewModel.FirstName,
                LastName = viewModel.LastName,
                CostPerHour = viewModel.CostPerHour
            };

            if (department != null)
            {
                user.Department = department;
                user.Manager = department.DepartmentHead;
                user.DepartmentId = department.Id;
            }

            return user;
        }

        public IEnumerable<User> ConvertFromViewModels(Dictionary<UserViewModel, Department> viewModels)
        {
            List<User> users = new List<User>();
            foreach (KeyValuePair<UserViewModel, Department> viewModel in viewModels)
            {
                users.Add(ConvertFromViewModel(viewModel.Key, viewModel.Value));
            }

            return users;
        }

        public UserViewModel ConvertToViewModel(User user)
        {
            UserViewModel viewModel = new UserViewModel
            {
                Id = user.Id,
                CostPerHour = user.CostPerHour,
                Email = user.Email,
                FirstName = user.FirstName,
                LastName = user.LastName,
                FullName = string.Format("{0} {1}", user.FirstName, user.LastName),
                Department = user.Department
            };

            if (user.Department != null)
                viewModel.DepartmentId = user.Department.Id;

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
