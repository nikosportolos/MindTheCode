using System;
using System.Collections.Generic;
using System.Diagnostics.CodeAnalysis;
using System.Linq;
using System.Threading.Tasks;

namespace Timesheet.Models.Entities
{
    public class TimesheetEntry
    {
        public int ID { get; set; }

        public DateTime EntryDate { get; set; }

        [NotNull]
        public User User { get; set; }

        [NotNull]
        public Project Project { get; set; }

        public int HoursWorked { get; set; }

        public TimesheetEntry()
        {
            EntryDate = DateTime.Now;
            User = new User();
            Project = new Project();
        }
    }
}
