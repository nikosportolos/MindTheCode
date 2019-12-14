using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Identity;
using Microsoft.AspNetCore.Mvc;
using Timesheet.Mappers;
using Timesheet.Models.Entities;
using Timesheet.Models.StatisticsModel;
using Timesheet.Repositories;

namespace Timesheet.Controllers
{
    public class StatisticsController : Controller
    {
        private readonly ITimesheetEntryRepository _timesheetEntryRepository;
        private readonly IProjectRepository _projectRepository;
        private readonly IDepartmentRepository _departmentRepository;
        private readonly IUserRepository _userRepository;
        private readonly ITimesheetEntryMapper _mapper;
        private readonly UserManager<User> _userManager;

        public StatisticsController(ITimesheetEntryRepository timesheetEntryRepository, ITimesheetEntryMapper mapper, UserManager<User> userManager, IProjectRepository projectRepository, IUserRepository userRepository, IDepartmentRepository departmentRepository)
        {
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

            Random rnd = new Random();
            var lstModel = new List<SimpleReportViewModel>();
            lstModel.Add(new SimpleReportViewModel
            {
                DimensionOne = "Beer",
                Quantity = rnd.Next(10)
            });
            lstModel.Add(new SimpleReportViewModel
            {
                DimensionOne = "Wine",
                Quantity = rnd.Next(10)
            });
            lstModel.Add(new SimpleReportViewModel
            {
                DimensionOne = "Whisky",
                Quantity = rnd.Next(10)
            });
            lstModel.Add(new SimpleReportViewModel
            {
                DimensionOne = "Water",
                Quantity = rnd.Next(10)
            });

            return View(lstModel);
        }

        public async Task<IActionResult> ProjectTimeChartAsync()
        {
            /*Add hours of timesheets in each project they belong*/
            var lstModel = new List<SimpleReportViewModel>();
            int cost = 0;
            List<TimesheetEntry> entries = (await _timesheetEntryRepository.GetAll()).ToList();
            List<Project> projects = (await _projectRepository.GetAll()).ToList();
            foreach(var x in projects)
            {
                foreach(var y in entries)
                {
                    if(y.ProjectId == x.Id)
                    {
                        cost += y.HoursWorked;
                    }
                }
                lstModel.Add(new SimpleReportViewModel
                {
                    DimensionOne = x.Name,
                    Quantity = cost
                });
                cost = 0;
            }

            /*
            Random rnd = new Random();
            
            lstModel.Add(new SimpleReportViewModel
            {
                DimensionOne = "Beer",
                Quantity = rnd.Next(10)
            });
            */

            return View(lstModel);           
        }


        public IActionResult ProjectOverviewChart()
        {
            return View();
        }

    }
}