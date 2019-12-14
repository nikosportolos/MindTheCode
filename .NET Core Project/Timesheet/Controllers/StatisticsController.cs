using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Timesheet.Data;
using Timesheet.Mappers;
using Timesheet.Models.Entities;
using Timesheet.Mappers;
using Timesheet.Models.Entities;
using Timesheet.Models.StatisticsModel;
using Timesheet.Repositories;

namespace Timesheet.Controllers
{
    public class StatisticsController : Controller
    {
        private readonly ApplicationDbContext _dbContext;
        private readonly ITimesheetEntryRepository _timesheetEntryRepository;
        private readonly IProjectRepository _projectRepository;
        private readonly IDepartmentRepository _departmentRepository;
        private readonly IUserRepository _userRepository;
        private readonly ITimesheetEntryMapper _mapper;
        private readonly UserManager<User> _userManager;

        public StatisticsController(ApplicationDbContext dbContext, ITimesheetEntryRepository timesheetEntryRepository, ITimesheetEntryMapper mapper, UserManager<User> userManager, IProjectRepository projectRepository, IUserRepository userRepository, IDepartmentRepository departmentRepository)
        {
            _dbContext = dbContext;
            _timesheetEntryRepository = timesheetEntryRepository;
            _projectRepository = projectRepository;
            _userRepository = userRepository;
            _departmentRepository = departmentRepository;
            _mapper = mapper;
            _userManager = userManager;
        }
        public ActionResult Index()
        {
            return View();
        }

        public IActionResult ProjectCostChart()
        {
            var department_partialCost = from user in _dbContext.Users
                                         join entry in _dbContext.TimesheetEntries on user.Id equals entry.User.Id
                                         join project in _dbContext.Projects on entry.ProjectId equals project.Id
                                         join department in _dbContext.Departments on project.DepartmentOwnerId equals department.Id
                                         select new { department.Name, Cost = user.CostPerHour * entry.HoursWorked };

            var groupCostByDepartment = from result in department_partialCost
                                        group result by result.Name into departments
                                        select new { departments.Key, TotalCost = departments.Sum(d => d.Cost) };

            List<CostPerDepartmentViewModel> lstModel = new List<CostPerDepartmentViewModel>();

            foreach (var element in groupCostByDepartment)
            {
                lstModel.Add(new CostPerDepartmentViewModel
                {
                    Department = element.Key,
                    Cost = element.TotalCost
                });
            }

            return View(lstModel);
        }

        public async Task<IActionResult> ProjectTimeChartAsync()
        {
            /*Add hours of timesheets in each project they belong*/
            var lstModel = new List<CostPerDepartmentViewModel>();
            int cost = 0;
            List<TimesheetEntry> entries = (await _timesheetEntryRepository.GetAll()).ToList();
            List<Project> projects = (await _projectRepository.GetAll()).ToList();

            foreach (var x in projects)
            {
                foreach (var y in entries)
                {
                    if (y.ProjectId == x.Id)
                    {
                        cost += y.HoursWorked;
                    }
                }

                lstModel.Add(new CostPerDepartmentViewModel
                {
                    Department = x.Name,
                    Cost = cost
                });

                cost = 0;
            }

            return View(lstModel);
        }


        public async Task<IActionResult> ProjectOverviewChartAsync()
        {
            Random rnd = new Random();
            List<TimesheetEntry> entries = (await _timesheetEntryRepository.GetAll()).ToList();
            //list of countries  
            var lstModel = new List<CostPerDepartmentViewModel>();
            
            lstModel.Add(new CostPerDepartmentViewModel
            {
                Department = "January",
                Cost = entries.Where(x => x.EntryDate.Month == 1 && x.EntryDate.Year == 2019).Sum(x=>x.HoursWorked)
            });;
            lstModel.Add(new CostPerDepartmentViewModel
            {
                Department = "February",
                Cost = entries.Where(x => x.EntryDate.Month == 2 && x.EntryDate.Year == 2019).Sum(x => x.HoursWorked)
            });
            lstModel.Add(new CostPerDepartmentViewModel
            {
                Department = "March",
                Cost = entries.Where(x => x.EntryDate.Month == 3 && x.EntryDate.Year == 2019).Sum(x => x.HoursWorked)
            });
            lstModel.Add(new CostPerDepartmentViewModel
            {
                Department = "April",
                Cost = entries.Where(x => x.EntryDate.Month == 4 && x.EntryDate.Year == 2019).Sum(x => x.HoursWorked)
            });
            lstModel.Add(new CostPerDepartmentViewModel
            {
                Department = "May",
                Cost = entries.Where(x => x.EntryDate.Month == 5 && x.EntryDate.Year == 2019).Sum(x => x.HoursWorked)
            });
            lstModel.Add(new CostPerDepartmentViewModel
            {
                Department = "June",
                Cost = entries.Where(x => x.EntryDate.Month == 6 && x.EntryDate.Year == 2019).Sum(x => x.HoursWorked)
            });
            lstModel.Add(new CostPerDepartmentViewModel
            {
                Department = "July",
                Cost = entries.Where(x => x.EntryDate.Month == 7 && x.EntryDate.Year == 2019).Sum(x => x.HoursWorked)
            });
            lstModel.Add(new CostPerDepartmentViewModel
            {
                Department = "August",
                Cost = entries.Where(x => x.EntryDate.Month == 8 && x.EntryDate.Year == 2019).Sum(x => x.HoursWorked)
            });
            lstModel.Add(new CostPerDepartmentViewModel
            {
                Department = "September",
                Cost = entries.Where(x => x.EntryDate.Month == 9 && x.EntryDate.Year == 2019).Sum(x => x.HoursWorked)
            });
            lstModel.Add(new CostPerDepartmentViewModel
            {
                Department = "October",
                Cost = entries.Where(x => x.EntryDate.Month == 10 && x.EntryDate.Year == 2019).Sum(x => x.HoursWorked)
            });
            lstModel.Add(new CostPerDepartmentViewModel
            {
                Department = "November",
                Cost = entries.Where(x => x.EntryDate.Month == 11 && x.EntryDate.Year == 2019).Sum(x => x.HoursWorked)
            });
            lstModel.Add(new CostPerDepartmentViewModel
            {
                Department = "December",
                Cost = entries.Where(x => x.EntryDate.Month == 12 && x.EntryDate.Year == 2019).Sum(x => x.HoursWorked)
            });

            return View(lstModel);
        }

    }
}