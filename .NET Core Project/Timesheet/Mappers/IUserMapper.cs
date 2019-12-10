using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Timesheet.Models.Entities;
using Timesheet.Models.ViewModels;

namespace Timesheet.Mappers
{
    public interface IUserMapper
    {
        public User ConvertFromViewModel(UserViewModel viewModel);
        public IEnumerable<User> ConvertFromViewModels(IEnumerable<UserViewModel> viewModels);

        public UserViewModel ConvertToViewModel(User user);
        public IEnumerable<UserViewModel> ConvertToViewModels(IEnumerable<User> users);
    }
}
