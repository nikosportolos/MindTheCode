using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Timesheet.Mappers;
using Timesheet.Models.Entities;
using Timesheet.Models.ViewModels;
using Timesheet.Repositories;

namespace Timesheet.Controllers
{
    public class UserController : Controller
    {
        private readonly IUserRepository _userRepository;
        private readonly UserManager<User> _userManager;
        private readonly IUserMapper _mapper;

        public UserController([FromServices] IUserRepository userRepository, IUserMapper mapper, UserManager<User> userManager)
        {
            _userRepository = userRepository;
            _mapper = mapper;
            _userManager = userManager;
        }

        // GET: User
        public async Task<IActionResult> Index()
        {
            List<User> users = (await _userRepository.GetAll()).ToList();
            return View(_mapper.ConvertToViewModels(users));
        }

        // GET: User/Details/5
        public ActionResult Details(int id)
        {
            return View();
        }

        // GET: User/Create
        public ActionResult Create()
        {
            return View();
        }

        // POST: User/Create
        [HttpPost]
        public async Task<IActionResult> Create(UserViewModel viewModel)
        {
            User user = _mapper.ConvertFromViewModel(viewModel);
            await _userRepository.Create(user);
            return RedirectToAction(nameof(Index));
        }

        // GET: User/Edit/5
        public async Task<IActionResult> Edit(string guid)
        {
            User user = await _userRepository.GetByGuid(guid);
            return View(_mapper.ConvertToViewModel(user));
        }

        // POST: User/Edit/5
        [HttpPost]
        public async Task<IActionResult> Edit(UserViewModel viewModel)
        {
            User user = _mapper.ConvertFromViewModel(viewModel);
            await _userRepository.Update(user);
            return RedirectToAction(nameof(Index));
        }

        // GET: User/Delete/5
        public async Task<IActionResult> Delete(string guid)
        {
            User user = await _userRepository.GetByGuid(guid);
            return View(_mapper.ConvertToViewModel(user));
        }

        // POST: User/Delete/5
        [HttpPost]
        public async Task<IActionResult> Delete(UserViewModel viewModel)
        {
            User user = _mapper.ConvertFromViewModel(viewModel);
            await _userRepository.Delete(user.Id);
            return RedirectToAction(nameof(Index));
        }
    }
}